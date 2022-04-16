class RemoveTimestampsFromForum < ActiveRecord::Migration[5.1]
  def change
    remove_column :forums, :created_at, :string
    remove_column :forums, :updated_at, :string
  end
end
