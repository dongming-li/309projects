#!/bin/bash

echo
echo "==============="
echo "Installing Gems"
echo "==============="
echo

bundle install

echo
echo "============================="
echo "Fixing bcrypt and devise Gems"
echo "============================="
echo

gem list bcrypt | cut -d" " -f1 | xargs gem uninstall -aIx
gem uninstall bcrypt
gem uninstall bcrypt-ruby
gem install bcrypt --platform=ruby
bundle install

echo
echo "========"
echo "Complete"
echo "========"
echo

while true; do
    read -p "Set RAILS_ENV to production? (y/n): " yn
    case $yn in
        [Yy]* ) echo "rails db:migrate RAILS_ENV=production"; rails db:migrate RAILS_ENV=production; break;;
        [Nn]* ) echo "rails db:migrate RAILS_ENV=development"; rails db:migrate RAILS_ENV=development; exit;;
        * ) echo "Please answer yes or no.";;
    esac
done

echo
echo "***"
echo "========="
echo "Complete!"
echo "========="
echo "***"
echo
