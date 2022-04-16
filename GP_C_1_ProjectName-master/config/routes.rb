Rails.application.routes.draw do
  devise_for :users
  # For details on the DSL available within this file, see http://guides.rubyonrails.org/routing.html
  root 'pages#home_page', as: 'home'

  get 'posts' => 'posts#index', as: 'posts'
  get 'forum' => 'forums#show', as: 'forum'
  get 'forum/new' => 'forums#new', as: 'forum/new'
  get 'forum/:forum/edit' => 'forums#edit', as: 'edit_forum'
  patch '/forum.:forum_id' => 'forums#update'
  post 'forum' => 'forums#create', :as => :create_forum
  delete "forum.:forum_id" => 'forums#destroy'
  forum_path = '/forum'
  get 'lobby' => 'lobby_requests#index', as: 'lobby'
  get 'about' => 'pages#about', as: 'about'
  get 'resources' => 'resources#index', as: 'resources'
  get 'profile' => 'profiles#index', as: 'profile'
  get 'messenger' => 'messengers#index', as: 'messenger'

  resources :lobby_requests
  resources :news_posts
  resources :announcements
  resources :posts do
    resources :comments
  end

end
