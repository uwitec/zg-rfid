rem call LogicBackup.bat %DUMP_DIR%
rem ==========================================================================================
rem     ��������ʵ��֧��exp���ݿ��Oracle���ݿ�汾�����ݿ��߼����ݣ�����Ϊ���������ݵ�
rem dump_bak ���ݿ�Ŀ¼�£�Ȼ���dmp�ļ���rar��ʽ���뵱�챸������������Ŀ¼�£������Ժ�
rem �����ڻָ���
rem
rem ��Ҫ���õĲ���˵����
rem BAT_HOME���������������ڵ�Ŀ¼��
rem BKDIR���ǵ��ñ�������ʱ�������Ĳ��� dump_dir,��Ҫ���ݵ��������ļ�Ŀ¼��
rem BKFILE:��������
rem HHMMSS:����ʱ��
rem FilePrefix:�����ļ�����ǰ׺
rem UserName:���ݵ��û���
rem Password:���ݵ�����
rem NetServer:�������ݿ��NET������
rem ORA_HOME:ִ��exp�ļ���Ŀ¼
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
