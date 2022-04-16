class PostsController < ApplicationController


  def index
    @posts = Post.all
  end

  def new
    @post =  Post.new
  end

  def show
    @post = Post.find(params[:id])
  end

  def create
    #render plain: params[:post].inspect
    @post = Post.new(post_params)
    @post.username = current_user.email

    if(@post.save)
      redirect_to @post
    else
      render 'new'
    end
  end

  def edit
    @post = Post.find(params[:id])
  end

  def update
    @post = Post.find(params[:id])

    if(@post.update(post_params))
      redirect_to @post
    else
      render 'edit'
    end
  end

  def destroy
    @post = Post.find(params[:id])
    if(@post.comments != nil)
      @comments = @post.comments
      @comments.each do |comm|
        comm.destroy
      end
        @post.destroy
    end
    redirect_to forum_path
  end

  private def post_params
    params.require(:post).permit(:username, :title, :body, :forum_id)
  end
end
