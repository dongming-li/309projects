module LobbyRequestsHelper

  def elapsedTime time
    elapsed = ((Time.now.hour*3600) + (Time.now.min*60) + (Time.now.sec)) - time
    if elapsed < 60
      output = (elapsed).to_s + "s"
    elsif elapsed < 3600
      output = (elapsed/60).to_s + "m"
    else
      ouput = (elapsed/3600).to_s + "h"
    end
    return output
  end

end
