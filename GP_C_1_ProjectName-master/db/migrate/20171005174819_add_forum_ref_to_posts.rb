class AddForumRefToPosts < ActiveRecord::Migration[5.1]
  def change
    add_reference :posts, :forum, foreign_key: true
  end
end
