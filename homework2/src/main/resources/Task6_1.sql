SELECT name, SUM(value) AS sum FROM expenses, receivers
WHERE receiver=receivers.num GROUP BY name;