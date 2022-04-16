class HomesMailer < ApplicationMailer


  #Will need to double check mailer functionality for home posts
  def submitted(home)
    @homes = home

    mail to: "PlaceHolder@example.org", subject: "New Announcement on Overhead"
  end
end
