ALTER TABLE threads ALTER flagged SET DEFAULT 0;
ALTER TABLE threads ALTER points SET DEFAULT 0;
ALTER TABLE replies ALTER flagged SET DEFAULT 0;
ALTER TABLE replies ALTER points SET DEFAULT 0;
ALTER TABLE topics MODIFY creation_time datetime DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE threads MODIFY creation datetime DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE replies MODIFY creation datetime DEFAULT CURRENT_TIMESTAMP;

ALTER TABLE replies ALTER endorsed SET DEFAULT 0;