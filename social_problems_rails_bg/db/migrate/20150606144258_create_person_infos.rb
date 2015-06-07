class CreatePersonInfos < ActiveRecord::Migration
  def change
    create_table :person_infos do |t|
    	t.integer :user_id
	 	t.string :first_name
	 	t.string :town
	 	t.string :second_name
	 	t.string :last_name
	 	t.string :address
	 	t.string :mobile_num
      t.timestamps null: false
    end
  end
end
