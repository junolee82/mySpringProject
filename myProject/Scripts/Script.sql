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

/* Transaction */
update news_board set replyCnt = replyCnt + 1 where newsNo = 1;

select newsNo from news_reply where rNo = 2;

update news_board set replyCnt = (select count(newsNo) from news_reply where newsNo = news_board.newsNo) where newsNo > 0;

update news_board set viewCnt = viewCnt + 1 where newsNo = 1;

/* ATTACH */
create table news_attach(
	fullName VARCHAR(150) not null,
	newsNo INT not null,
	regDate TIMESTAMP default now(),
	primary key(fullName));
	
alter table news_attach add constraint fk_board_attach
foreign key(newsNo) references news_board(newsNo);

select * from news_attach;

insert into news_attach(fullName, newsNo) values('WIZ', LAST_INSERT_ID());

insert into news_attach(fullName, newsNo) values(#{fullName}, LAST_INSERT_ID())

select a.newsNo, a.nTitle, a.nContent, a.writer, a.ragDate, a.viewCnt, a.replyCnt, a.recommend, b.fullName from news_board a
left join news_attach b
on(a.newsNo = b.newsNo) where a.newsNo > 0 and nTitle like CONCAT('%', 'tinas', '%') order by a.newsNo desc, a.ragDate desc limit 0, 10;

select a.newsNo, a.nTitle, a.nContent, a.writer, a.ragDate, a.viewCnt, a.replyCnt, a.recommend, b.fullName from news_board a
left join news_attach b
on(a.newsNo = b.newsNo) where a.newsNo > 0 order by a.newsNo desc, a.ragDate desc limit 0, 10;

delete from news_attach where newsNo = 4;

insert into news_attach(fullName, newsNo) values(fullName, newsNo);
