class Post < ApplicationRecord
  belongs_to :forum
  has_many :comments
  validates :title, presence: true,
                    length: {minimum: 5}

  validates :body, presence: true,
                   length: {minimum: 1}
end
