class PersonInfosController < ApplicationController
	def change_info
		if not current_user.person_info
			@infos = PersonInfo.new user_id: current_user.person_info
			@infos.save
		else
			@infos = current_user.person_info
		end
	end

	def create
		current_user.person_info.update update_params

		redirect_to :back
	end

	def update_params
		params.require(:person_info).permit!
	end
end
