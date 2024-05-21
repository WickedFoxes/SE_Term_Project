-- --------------------------------------------------------
-- 호스트:                          C:\GitProject\SE_Term_Project\SE_Term_Project\SE_Term_Project.sqlite3
-- 서버 버전:                        3.39.4
-- 서버 OS:                        
-- HeidiSQL 버전:                  12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES  */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- 테이블 SE_Term_Project.Account 구조 내보내기
CREATE TABLE IF NOT EXISTS "Account" (
	"id" INTEGER NOT NULL, "userid" CHAR(50) NOT NULL, "password" CHAR(50) NOT NULL, "authority" CHAR(20) NOT NULL,
	PRIMARY KEY ("id"),
	CONSTRAINT "UK_userID" UNIQUE ("userid")
);

-- 테이블 SE_Term_Project.Comment 구조 내보내기
CREATE TABLE IF NOT EXISTS "Comment" (
	"id" INTEGER NOT NULL,
	"content" TEXT NOT NULL DEFAULT '',
	"created_date" DATETIME NOT NULL DEFAULT '',
	"writer" INTEGER NOT NULL,
	"issue_id" INTEGER NOT NULL,
	PRIMARY KEY ("id"),
	CONSTRAINT "FK__Account_to_Comment" FOREIGN KEY ("writer") REFERENCES "Account" ("id") ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT "FK__Issue" FOREIGN KEY ("issue_id") REFERENCES "Issue" ("id") ON UPDATE CASCADE ON DELETE CASCADE
);

-- 테이블 데이터 SE_Term_Project.Comment:-1 rows 내보내기
/*!40000 ALTER TABLE "Comment" DISABLE KEYS */;
/*!40000 ALTER TABLE "Comment" ENABLE KEYS */;

-- 테이블 SE_Term_Project.Issue 구조 내보내기
CREATE TABLE IF NOT EXISTS "Issue" (
	"id" INTEGER NOT NULL,
	"title" VARCHAR(200) NOT NULL DEFAULT '',
	"description" TEXT NOT NULL DEFAULT '',
	"reportedDate" DATETIME NOT NULL DEFAULT '',
	"priority" VARCHAR(20) NOT NULL DEFAULT '',
	"state" VARCHAR(20) NOT NULL DEFAULT '',
	"project_id" INTEGER NULL DEFAULT NULL,
	"reporter" INTEGER NULL DEFAULT NULL,
	"assignee" INTEGER NULL DEFAULT NULL,
	"fixer" INTEGER NULL DEFAULT NULL,
	PRIMARY KEY ("id"),
	CONSTRAINT "FK_Project_to_Issue" FOREIGN KEY ("project_id") REFERENCES "Project" ("id") ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT "FK_Reporter_to_Issue" FOREIGN KEY ("reporter") REFERENCES "Account" ("id") ON UPDATE CASCADE ON DELETE SET NULL,
	CONSTRAINT "FK_Assignee_to_Issue" FOREIGN KEY ("assignee") REFERENCES "Account" ("id") ON UPDATE CASCADE ON DELETE SET NULL,
	CONSTRAINT "FK_Fixer_to_Issue" FOREIGN KEY ("fixer") REFERENCES "Account" ("id") ON UPDATE CASCADE ON DELETE SET NULL
);

-- 테이블 데이터 SE_Term_Project.Issue:0 rows 내보내기
/*!40000 ALTER TABLE "Issue" DISABLE KEYS */;
/*!40000 ALTER TABLE "Issue" ENABLE KEYS */;

-- 테이블 SE_Term_Project.Project 구조 내보내기
CREATE TABLE IF NOT EXISTS "Project" (
	"id" INTEGER NOT NULL, "name" VARCHAR(200) NOT NULL, "created_date" DATETIME NULL,
	PRIMARY KEY ("id")
);

-- 테이블 데이터 SE_Term_Project.Project:-1 rows 내보내기
/*!40000 ALTER TABLE "Project" DISABLE KEYS */;
/*!40000 ALTER TABLE "Project" ENABLE KEYS */;

-- 테이블 SE_Term_Project.ProjectJoin 구조 내보내기
CREATE TABLE IF NOT EXISTS "ProjectJoin" (
	"id" INTEGER NOT NULL,
	"project_id" INTEGER NOT NULL,
	"account_id" INTEGER NOT NULL,
	PRIMARY KEY ("id"),
	CONSTRAINT "FK__Project_to_ProjecJjoin" FOREIGN KEY ("project_id") REFERENCES "Project" ("id") ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT "FK__Account_to_ProjecJjoin" FOREIGN KEY ("account_id") REFERENCES "Account" ("id") ON UPDATE CASCADE ON DELETE CASCADE
);

-- 테이블 데이터 SE_Term_Project.ProjectJoin:-1 rows 내보내기
/*!40000 ALTER TABLE "ProjectJoin" DISABLE KEYS */;
/*!40000 ALTER TABLE "ProjectJoin" ENABLE KEYS */;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
