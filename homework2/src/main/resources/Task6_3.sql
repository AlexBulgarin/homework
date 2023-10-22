SELECT max FROM(SELECT MAX(value) AS max, SUM(value) AS sum FROM expenses
GROUP BY paydate
ORDER BY sum DESC LIMIT 1) AS temp