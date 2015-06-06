class CreateProblems < ActiveRecord::Migration
	def change
		create_table :problems do |t|
			t.text :description, null: false
			t.integer :user_id
			t.string :picture
			t.float :lat_one
			t.float :lat_two
			t.timestamps null: false
		end
	end
end
