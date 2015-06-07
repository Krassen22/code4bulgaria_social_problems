class HomesController < ApplicationController
	def index
		@problems = Problem.where "user_id = ?", current_user
	end
end
