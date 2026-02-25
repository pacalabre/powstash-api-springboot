 ALTER TABLE mountains
     ADD COLUMN description TEXT NOT NULL,
     ADD COLUMN address VARCHAR(200),
     ADD COLUMN longitude VARCHAR(100) NOT NULL,
     ADD COLUMN latitude VARCHAR(100) NOT NULL,
     ADD COLUMN local_knowledge TEXT;