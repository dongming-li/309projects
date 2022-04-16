class CreateLobbyRequests < ActiveRecord::Migration[5.1]
  def change
    create_table :lobby_requests do |t|
      t.string :name
      t.string :game
      t.integer :time

      t.timestamps
    end
  end
end
