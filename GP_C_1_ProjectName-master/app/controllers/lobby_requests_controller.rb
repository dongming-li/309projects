class LobbyRequestsController < ApplicationController

  def index
    params[:sort] ||= "name"
    @lobby_requests = LobbyRequest.all
  end

  def new
    @lobby_request = LobbyRequest.new
  end

  def create
    @lobby_request = LobbyRequest.new(lobby_request_params)
    @lobby_request.name = current_user.email
    @lobby_request.time = (Time.now.hour*3600) + (Time.now.min*60) + (Time.now.sec)

    if(@lobby_request.save)
      redirect_to lobby_path
    else
      render 'new'
    end
  end

  def destroy
    @lobby_request = LobbyRequest.find(params[:id])
    @lobby_request.destroy

    redirect_to lobby_path
  end

  private def lobby_request_params
    params.require(:lobby_request).permit(:name, :game, :time)
  end

end
