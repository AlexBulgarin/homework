SELECT SUM(value) AS sum FROM expenses
WHERE paydate = (SELECT paydate FROM expenses WHERE value = (SELECT MAX(value) FROM expenses))