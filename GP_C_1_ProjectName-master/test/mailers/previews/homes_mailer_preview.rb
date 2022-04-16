# Preview all emails at http://localhost:3000/rails/mailers/homes_mailer
class HomesMailerPreview < ActionMailer::Preview

  # Preview this email at http://localhost:3000/rails/mailers/homes_mailer/submitted
  def submitted
    HomesMailer.submitted
  end

end
