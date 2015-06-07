class CreateProblems < ActiveRecord::Migration
	def change
		create_table :problems do |t|
			t.integer :user_id
			t.text :description, null: false
			t.string :picture
			t.string :receiver
			t.float :lat_one
			t.float :lat_two
			t.timestamps null: false
		end
	end
end
