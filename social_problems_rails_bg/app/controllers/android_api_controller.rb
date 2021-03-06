require 'digest/sha1'

class AndroidApiController < ApplicationController
	before_action :check_token, except: [:login, :register]

	skip_before_action :verify_authenticity_token
	skip_before_action :authenticate_user!

	def login
		token = get_user_token
		render plain: token
	end

	def get_user_token
		user = User.find_by email: params[:email]
		if user && user.valid_password?(params[:password])
			set_session user
		else
			set_error_messages "Error"
		end
	end

	def set_session user
		token = generate_token
		session = ApiToken.new token: token, user: user
		session.save ? token : "Error"
	end

	def generate_token
		Digest::SHA1.hexdigest params[:email] + ApiToken.count.to_s
	end

	def register
		@user = User.new user_params

		if @user.save && params[:confirm_password] == params[:password]
			render plain: "Success"
		else	
			set_user_errors "Can't register"
		end
	end

	def user_params
		params.permit(:email, :password)	
	end

	def set_user_errors custom_error
		render plain: custom_error || @user.errors.full_messages.join("\n")
	end

	def send_signal
		@problem = Problem.new send_signal_params
		@problem.user = current_user
		@problem.save
	end

	def current_user
		current_user ||= set_user
	end

	def set_user
		token = ApiToken.find_by(token: token_params)
		token.user
	end

	def check_token
        render(plain: "Error") if ApiToken.find_by(token: token_params).nil?
	end

    def token_params
        params.require(:token)
    end

	def send_signal_params
        params.permit(:description, :lat_two, :lat_one)
	end
end
