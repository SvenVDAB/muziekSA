insert into albums(artiestid, naam) values
((select id from artiesten where naam = 'test'), 'test');