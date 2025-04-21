SELECT 
f.id FoodId,
f.image_path FoodImagePath,
f.localhost LocalHost,
f."name" Name ,
f.english_name EnglishName,
f.price Price,
f.qty  Qty,
c.id CinemaId,
c."name" CinemaName,
c.en_name CinemaEnglishName,
c.code Code,
c.address Address,
c.path_image CinemaImagePath
FROM DBFOOD f
INNER JOIN DBCINEMA c
ON f.cinema_id = c.id

