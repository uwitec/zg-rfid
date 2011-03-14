rem call LogicBackup.bat %DUMP_DIR%
rem ==========================================================================================
rem     本批处理实现支持exp数据库的Oracle数据库版本的数据库逻辑备份，流程为：导出数据到
rem dump_bak 数据库目录下，然后把dmp文件以rar格式放入当天备份日期命名的目录下，便于以后按
rem 照日期恢复。
rem
rem 需要配置的参数说明：
rem BAT_HOME：即本批处理所在的目录；
rem BKDIR：是调用本批处理时传进来的参数 dump_dir,即要备份到的数据文件目录。
rem BKFILE:备份日期
rem HHMMSS:备份时间
rem FilePrefix:备份文件名的前缀
rem UserName:备份的用户名
rem Password:备份的密码
rem NetServer:连接数据库的NET服务名
rem ORA_HOME:执行exp文件的目录
rem Author: majy
rem Time: 2007-06-25
rem ==========================================================================================
rem
cd..
set dump_bak=F:\oracleDbBackUp\backup
set BKFILE=%Date:~0,4%%Date:~5,2%%Date:~8,2%
set HHMMSS=%time:~0,2%%time:~3,2%%time:~6,2%
set FilePrefix=FRAME
set UserName=frame
set Password=frame
set NetServer=rfid
set ORA_HOME=F:\oracleDbBackUp\backup
cd %ORA_HOME%
%HHMMSS%
exp %UserName%/%Password%@%NetServer% file=%dump_bak%\%FilePrefix%_%BKFILE%_%HHMMSS%.dmp
