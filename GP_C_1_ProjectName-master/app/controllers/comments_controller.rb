class CommentsController < ApplicationController

  def create
    @post = Post.find(params[:post_id])

    @comment = @post.comments.create(comment_params)
    @comment.username = current_user.email

    redirect_to post_path(@post)
  end

  def edit
    @post = Post.find(params[:id])
    @comment = @post.comments.find(params[:id])
  end

  def update
    @post = Post.find(params[:id])
    @comment = @post.comments.find(params[:id])

    if(@comment.update(comment_params))
      redirect_to @post
    else
      render 'edit'
    end
  end

  def destroy
    @post = Post.find(params[:post_id])
    @comment = @post.comments.find(params[:id])
    @comment.destroy
    redirect_to post_path(@post)
  end
  private def comment_params
    params.require(:comment).permit(:username, :body)
  end
end
