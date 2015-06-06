class ProblemsController < ApplicationController
	def new 
		@problem = Problem.new
	end

	def create
		@problem = Problem.new problem_params
		@problem.user = current_user
		if @problem.save
			flash[:notice] = "New problem was created successfully"
			redirect_to :back
		else
			flash[:alert] = @problem.errors.full_messages.join("<br />")
			render 'new'
		end
	end

	def problem_params
		params.require(:problem).permit!
	end
end
