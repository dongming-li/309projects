class PagesController < ApplicationController
  def home_page
    @news_posts = NewsPost.all
    @announcements = Announcement.all
  end
end
