DROP TABLE IF EXISTS task;

CREATE TABLE task (
  id Long AUTO_INCREMENT  PRIMARY KEY,
  task_token VARCHAR(250)  NOT NULL,
  created_at VARCHAR(250) NOT NULL,
  expiring_at VARCHAR(250) NOT NULL,
  updated_at VARCHAR(250) NOT NULL,
  channel VARCHAR(250) DEFAULT NULL,
  conversation_id VARCHAR(250) DEFAULT NULL,
  country VARCHAR(250) DEFAULT NULL,
  type VARCHAR(250) DEFAULT NULL,
  blob BLOB ,
  status VARCHAR(250) DEFAULT NULL
);

