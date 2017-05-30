/* korea_hiphop */

/* NEWS TABLE */
create table news_board(
	newsNo INT not null AUTO_INCREMENT,
	nTitle VARCHAR(200) not null,
	nContent TEXT null,
	writer VARCHAR(50) not null,
	ragDate DATETIME not null default now(),
	viewCnt INT default 0,
	replyCnt INT default 0,
	recommend INT default 0,
	thumbnailImg VARCHAR(200),
	PRIMARY KEY (newsNo));

select * from news_board;

insert into news_board(nTitle, nContent, writer) values('제목입니다.', '내용입니다.', '이준호');

select * from news_board where newsNo = 1;

select newsNo, nTitle, nContent, writer, ragDate, viewCnt, replyCnt, recommend, thumbnailImg from news_board where newsNo > 0 order by newsNo desc, ragDate desc;

update news_board set nTitle = '제목입니다. 수정', nContent = '내용입니다. 수정' where newsNo = 1;

delete from news_board where newsNo = 1;


select * from news_board where newsNo > 0 order by newsNo desc limit 0, 10;

select count(newsNo) from news_board where newsNo > 0;

/* NEWS REPLY */
create table news_reply(
	rNo INT not null AUTO_INCREMENT,
	newsNo INT not null default 0,
	replyText VARCHAR(1000) not null,
	replyer VARCHAR(50) not null,
	regDate TIMESTAMP not null default now(),
	updateDate TIMESTAMP not null default now(),
	primary key(rNo));

alter table news_reply add constraint fk_board
foreign key(newsNo) references news_board(newsNo);
	
select * from news_reply;

select * from news_reply where newsNo = 1 order by rNo desc;

insert into news_reply(newsNo, replyText, replyer) values(1, '위즈칼리파', '이준호');

update news_reply set replyText = '위즈칼리파 / wiz khalifa', updatedate = now() where rNo = 1;

delete from news_reply where rNo = 1;

select * from news_reply where newsNo = 1 order by rNo desc limit 0, 10;

select count(newsNo) from news_reply where newsNo = 1;
