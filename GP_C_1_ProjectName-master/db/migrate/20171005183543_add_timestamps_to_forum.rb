class AddTimestampsToForum < ActiveRecord::Migration[5.1]
  def change
    add_column :forums, :created_at, :string
    add_column :forums, :updated_at, :string
  end
end
