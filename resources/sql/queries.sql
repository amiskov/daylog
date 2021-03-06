-- ------------------------------------------------
-- DEEDS
-- :name save-deed! :! :n
-- :doc creates a new deed record
INSERT INTO deeds (title) VALUES (:title)

-- :name update-deed! :! :n
-- :doc updates an existing deed record
UPDATE deeds SET title = :title WHERE id = :id

-- :name get-deeds :? :*
-- :doc retrieves all deeds
SELECT * FROM deeds

-- :name get-deed :? :1
-- :doc retrieves an deed record given the id
SELECT * FROM deeds WHERE id = :id

-- :name delete-deed! :! :n
-- :doc deletes a deed record given the id
DELETE FROM deeds WHERE id = :id

-- ------------------------------------------------
-- GRADES
-- :name save-grade! :! :n
-- :doc creates a new grade record
INSERT INTO grades (title) VALUES (:title)

-- :name update-grade! :! :n
-- :doc updates an existing grade record
UPDATE grades SET title = :title WHERE id = :id

-- :name get-grades :? :*
-- :doc retrieves all grade records
SELECT * FROM grades

-- :name get-grade :? :1
-- :doc retrieves a grade record given the id
SELECT * FROM grades WHERE id = :id

-- :name delete-grade! :! :n
-- :doc deletes a grade record given the id
DELETE FROM grades WHERE id = :id

-- ------------------------------------------------
-- ENTRIES
-- :name save-entry! :! :n
-- :doc creates a new entry record
INSERT INTO entries (deed_id, grade_id)
VALUES (:deed_id, :grade_id)

-- :name get-entries :? :*
-- :doc retrieves all entries
SELECT
	deeds.title as deed_title,
	grades.title as grade_title,
	entries.timestamp_start as timestamp_start
FROM entries
JOIN deeds ON entries.deed_id = deeds.id
JOIN grades ON entries.grade_id = grades.id
ORDER BY timestamp_start DESC;

-- :name get-entry :? :1
-- :doc retrieves an entry record given the id
SELECT * FROM entries WHERE id = :id

-- :name delete-entry! :! :n
-- :doc deletes a entry record given the id
DELETE FROM entries WHERE id = :id
