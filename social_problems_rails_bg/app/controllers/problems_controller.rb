class ProblemsController < ApplicationController
	def new 
		@problem = Problem.new
	end

	def create
		@problem = Problem.new problem_params
		upload_file if params[:problem][:file]
		@problem.user = current_user
		if @problem.save
			flash[:notice] = "Сигнализирането за нередност е създадено успешно"
			redirect_to :back
		else
			flash[:alert] = @problem.errors.full_messages.join("<br />")
			render 'new'
		end
	end

	def upload_file
	    name = params[:problem][:file].original_filename
	    directory = "public/images/upload"
	    path = File.join(directory, name)
	    File.open(path, "wb") { |f| f.write(params[:problem][:file].read) }
	    @problem.picture = path
	end

	def show
		@problem = Problem.find params[:id]
	end

	def index
		render 'new'
	end

	private

	def problem_params
		params.require(:problem).permit(:lat_one, :lat_two, :description)
	end
end
