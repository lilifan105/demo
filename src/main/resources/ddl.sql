CREATE TABLE MESSAGE(
    id SERIAL, 
    name VARCHAR(255),
    condition VARCHAR(255)
);

CREATE TABLE USER_ACCOUNT(
    id SERIAL, 
    name VARCHAR(255),
    password VARCHAR(255)
);

-- 顧客
--* RestoreFromTempTable
create table kokyaku (
  kokyakucode char(5) not null
  , kokyakumeisyo varchar(255) not null
  , constraint kokyaku_PKC primary key (kokyakucode)
) ;

-- 工程
--* RestoreFromTempTable
create table kotei (
  koteicode char(4) not null
  , koteimeisyo varchar(100) not null
  , koteiteigi varchar(1024) not null
  , constraint kotei_PKC primary key (koteicode)
) ;

-- 日次作業実績明細
--* RestoreFromTempTable
create table nitizisagyozissekimeisai (
  nitizisagyozissekiid bigint not null
  , meisaibango int not null
  , projectcode char(10) not null
  , sagyokoteicode char(4) not null
  , sagyozikan int not null
  , tokkiziko varchar(255)
  , constraint nitizisagyozissekimeisai_PKC primary key (nitizisagyozissekiid,meisaibango)
) ;

-- 部門
--* RestoreFromTempTable
create table bumon (
  bumoncode char(4) not null
  , bumonmeisyo varchar(255) not null
  , bumonryakusyo varchar(255) not null
  , constraint bumon_PKC primary key (bumoncode)
) ;

-- プロジェクト
--* RestoreFromTempTable
create table project (
  projectcode char(10) not null
  , projectmeisyo varchar(255) not null
  , kokyakucode char(5) not null
  , kaisibi date not null
  , syuryobi date not null
  , projectkubun char(4) not null
  , constraint project_PKC primary key (projectcode)
) ;

-- 社員
--* RestoreFromTempTable
create table syain (
  syainbango IDENTITY not null
  , sei varchar(50) not null
  , mei varchar(50) not null
  , nyusyanengappi date not null
  , syozokubumoncode char(4) not null
  , useraccountid bigint not null
  , constraint syain_PKC primary key (syainbango)
) ;

-- 日次作業実績
--* RestoreFromTempTable
create table nitizisagyozisseki (
  id IDENTITY not null
  , syainbango char(6) not null
  , sagyobi date not null
  , constraint nitizisagyozisseki_PKC primary key (id)
) ;

