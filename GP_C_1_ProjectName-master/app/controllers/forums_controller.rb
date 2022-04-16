class ForumsController < ApplicationController

  def index
    @forums = Forum.all
  end

  def new
    @forum = Forum.new
  end

  def show
    @forums = Forum.all
  end

  def create
    @forum = Forum.new(forum_params)

    if(@forum.save)
      redirect_to forum_path
    else
      render 'new'
    end
  end

  def edit
    @forum = Forum.find(params[:forum])
  end

  def update
    @forum = Forum.find(params[:forum_id])

    if(@forum.update(forum_params))
      redirect_to forum_path
    else
      render 'edit'
    end
  end

  def destroy
    @forum = Forum.find(params[:forum_id])

    if(@forum.posts != nil)
      @posts = @forum.posts
      @posts.each do |post|
        if(post.comments != nil)
          @comments = post.comments
          @comments.each do |comm|
            comm.destroy
          end
        end
        post.destroy
      end
    end
    @forum.destroy
    redirect_to forum_path
  end

  private def forum_params
    params.require(:forum).permit(:name)
  end

end
