class PersonInfosController < ApplicationController
	def change_info
		if not current_user.person_info
			@infos = PersonInfo.new user_id: current_user
		else
			@infos = current_user.person_info
		end
	end

	def create
		if current_user.person_info
			current_user.person_info.update update_params
		else
			current_user.person_info = PersonInfo.new update_params
			current_user.save
		end

		redirect_to :back
	end

	def update_params
		params.require(:person_info).permit!
	end
end
