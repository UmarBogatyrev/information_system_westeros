DO $$ 
DECLARE
  start_time timestamp;
  end_time timestamp;
BEGIN
  start_time := clock_timestamp();
  -- МЕЖДУ ЭТИМИ КОММЕНТАМИ БЛОКИ INSERT С ПРОВЕРКОЙ
    -- START Правильные блоки записи и обновления
  BEGIN
    INSERT INTO westerostax.users (id, active, password, username, district_id, landowner_id, lord_id, region_id) VALUES
      (2, true, '$2a$12$jLE90NkJ16kbKK4knPTmfeXYqz7oLplG2DiP51GHwSyqLKNsCXQxm', 'lord1', NULL, NULL, NULL, NULL),
      (3, true, '$2a$12$gMjJEBEqb0Q0VdBvPfl5BePJtdunnjZW6nxYNEraclpqs3SpBf8Wa', 'lord2', NULL, NULL, NULL, NULL),
      (4, true, '$2a$12$OS5MAmyOabqunt3S4KRzNuifXhD.i9qSEsE7LZpD0Nlfh.zXOW.Ma', 'lord3', NULL, NULL, NULL, NULL),
      (5, true, '$2a$12$I4KR2GUlDeJTiZNdExM5IufBRY9jzJbBGXIQAW9GeAAYWRZsoQpC2', 'lord4', NULL, NULL, NULL, NULL);
  EXCEPTION
    WHEN others THEN
      RAISE NOTICE 'Error inserting user: %', SQLERRM;
  END;

  BEGIN
    INSERT INTO westerostax.user_role (user_id, roles) VALUES
      (2, 'ROLE_LORD'),
      (3, 'ROLE_LORD'),
      (4, 'ROLE_LORD'),
      (5, 'ROLE_LORD');
  EXCEPTION
    WHEN others THEN
      RAISE NOTICE 'Error inserting user: %', SQLERRM;
  END;

  BEGIN
    INSERT INTO westerostax.regions (id, name, total_households, total_cattle_headcount, total_residents, total_taxes_paid, total_income, lord_id) VALUES 
      (1, 'Region1', 0, 0, 0, 0, 0, 2),
      (2, 'Region2', 0, 0, 0, 0, 0, 3),
      (3, 'Region3', 0, 0, 0, 0, 0, 4),
      (4, 'Region4', 0, 0, 0, 0, 0, 5);
  EXCEPTION
    WHEN others THEN
      RAISE NOTICE 'Error inserting user: %', SQLERRM;
  END;

  BEGIN
    INSERT INTO westerostax.users (id, active, password, username, district_id, landowner_id, lord_id, region_id) VALUES
      (6, true, '$2a$12$QToTs2ACvGeuu71M5Ue.eOxnef7Jpvr4akLonDt2W7yaAKkg61bj2', 'landowner1', NULL, NULL, 2, 1),
      (7, true, '$2a$12$jWpRClcYa2ntn.qU8hEgqeLPwRKgDiHxTquRoWb3GvgWPyER3WXgq', 'landowner2', NULL, NULL, 3, 2),
      (8, true, '$2a$12$dbITYjihIRxHlMymnKK9Ze60uwbeRTstzpZFn2zig.j3rJuoIVbqO', 'landowner3', NULL, NULL, 4, 3),
      (9, true, '$2a$12$jxN30gMc8IJ7QTuPz9khF./MZiA7Y6Buo0/DV8gKktJki0SEqsGnG', 'landowner4', NULL, NULL, 5, 4);
  EXCEPTION
    WHEN others THEN
      RAISE NOTICE 'Error inserting user: %', SQLERRM;
  END;

  BEGIN
    INSERT INTO westerostax.user_role (user_id, roles) VALUES
      (6, 'ROLE_LANDOWNER'),
      (7, 'ROLE_LANDOWNER'),
      (8, 'ROLE_LANDOWNER'),
      (9, 'ROLE_LANDOWNER');
  EXCEPTION
    WHEN others THEN
      RAISE NOTICE 'Error inserting user: %', SQLERRM;
  END;

  BEGIN
    INSERT INTO westerostax.districts (id, name, total_households, total_cattle_headcount, total_residents, total_taxes_paid, total_income, region_id) VALUES 
      (1, 'District1', 0, 0, 0, 0, 0, 1),
      (2, 'District2', 0, 0, 0, 0, 0, 2),
      (3, 'District3', 0, 0, 0, 0, 0, 3),
      (4, 'District4', 0, 0, 0, 0, 0, 4);
  EXCEPTION
    WHEN others THEN
      RAISE NOTICE 'Error inserting user: %', SQLERRM;
  END;

  BEGIN
    INSERT INTO westerostax.users (id, active, password, username, district_id, landowner_id, lord_id, region_id) VALUES
      (10, true, '$2a$12$.EqAA4uJPkvjwzq5SRcg7eTHnyYxzxB36P0Gl7Zf3sBKKDjIo.VDO', 'courier1', 1, 6, NULL, NULL),
      (11, true, '$2a$12$k5jWPa1U4p77/aEaRGAIl.95Ovq37O2B9QgT6EdduWNnFx0N8XXDK', 'courier2', 2, 7, NULL, NULL),
      (12, true, '$2a$12$CDWPhoj9k53NMbf/CRGh9.Ex8tqtAv37hComv6ttceI07GpvACbPO', 'courier3', 3, 8, NULL, NULL),
      (13, true, '$2a$12$DKziIyhm0kbJjLwXsVx9EOzRA80KS6upOITKwVipgwcDTdQ1.nK/W', 'courier4', 4, 9, NULL, NULL);
  EXCEPTION
    WHEN others THEN
      RAISE NOTICE 'Error inserting user: %', SQLERRM;
  END;

  BEGIN
    INSERT INTO westerostax.user_role (user_id, roles) VALUES
      (10, 'ROLE_COURIER'),
      (11, 'ROLE_COURIER'),
      (12, 'ROLE_COURIER'),
      (13, 'ROLE_COURIER');
  EXCEPTION
    WHEN others THEN
      RAISE NOTICE 'Error inserting user: %', SQLERRM;
  END;

  BEGIN
    INSERT INTO westerostax.census (id, comment, date_begin, date_end, finished, total_cattle_headcount, total_households, total_land_area, total_resident_count, lord_id) VALUES
      (1, 'censustest', '2020-02-20', '2021-02-20', false, 0, 0, 0, 0, 2);
  EXCEPTION
    WHEN others THEN
      RAISE NOTICE 'Error inserting user: %', SQLERRM;
  END;

  BEGIN
    INSERT INTO westerostax.households (id, address, cattle_headcount, income, land_area, name, resident_count, taxes_collected, courier_id, district_id) VALUES
      (1, 'address1', 5 , 5, 5, 'name1', 5, 5, NULL, 1);
  EXCEPTION
    WHEN others THEN
      RAISE NOTICE 'Error inserting user: %', SQLERRM;
  END;

  BEGIN
    UPDATE westerostax.census
    SET finished = true
    WHERE id = 1;
  EXCEPTION
    WHEN others THEN
      RAISE NOTICE 'Error inserting user: %', SQLERRM;
  END;

  BEGIN
    INSERT INTO westerostax.tax_type (id, description, formula, name, lord_id) VALUES
      (1, 'NALOG PLATI', '15', 'NALOG1', 2);
  EXCEPTION
    WHEN others THEN
      RAISE NOTICE 'Error inserting user: %', SQLERRM;
  END;

  BEGIN
    INSERT INTO westerostax.tax (id, comment, date_begin, date_end, finished, total_income, tax_type_id) VALUES
      (1, 'PLATI', '2021-02-22', '2021-03-21', false, 0, 1);
  EXCEPTION
    WHEN others THEN
      RAISE NOTICE 'Error inserting user: %', SQLERRM;
  END;

  BEGIN
    UPDATE westerostax.tax
    SET finished = true
    WHERE id = 1;
  EXCEPTION
    WHEN others THEN
      RAISE NOTICE 'Error inserting user: %', SQLERRM;
  END;
  RAISE NOTICE '    Успешно занесен системообразущий набор данных в таблицы';
  RAISE NOTICE ' ';
    -- END Правильные блоки записи и обновления
  -- \\\\\\\\\\\\\\\\\\\
    -- START Неправильные блоки записи и обновления
  BEGIN
    INSERT INTO westerostax.users (id, active, password, username, district_id, landowner_id, lord_id, region_id) VALUES
      (6, true, '$2a$12$QToTs2ACvGeuu71M5Ue.eOxnef7Jpvr4akLonDt2W7yaAKkg61bj2', 'landowner1', NULL, NULL, 2, 1),
      (7, true, '$2a$12$jWpRClcYa2ntn.qU8hEgqeLPwRKgDiHxTquRoWb3GvgWPyER3WXgq', 'landowner2', NULL, NULL, 3, 2),
      (8, true, '$2a$12$dbITYjihIRxHlMymnKK9Ze60uwbeRTstzpZFn2zig.j3rJuoIVbqO', 'landowner3', NULL, NULL, 4, 3),
      (9, true, '$2a$12$jxN30gMc8IJ7QTuPz9khF./MZiA7Y6Buo0/DV8gKktJki0SEqsGnG', 'landowner4', NULL, NULL, 5, 4);
  EXCEPTION
    WHEN others THEN
      RAISE NOTICE 'Error inserting user: %', SQLERRM;
  END;

  BEGIN
    INSERT INTO westerostax.tax_type (id, description, formula, name, lord_id) VALUES
      (1, 'NALOG PLATI', '15', 'NALOG1', 2);
  EXCEPTION
    WHEN others THEN
      RAISE NOTICE 'Error inserting user: %', SQLERRM;
  END;

    BEGIN
    INSERT INTO westerostax.districts (id, name, total_households, total_cattle_headcount, total_residents, total_taxes_paid, region_id) VALUES 
      (1, 2, 0, 0, true, 0, 1),
      (2, 'District2', 0, 0, 0, 0, 2),
      (3, 'District3', 0, 0, 0, 0, 3),
      (4, 'District4', 0, 0, 0, 0, 4);
  EXCEPTION
    WHEN others THEN
      RAISE NOTICE 'Error inserting user: %', SQLERRM;
  END;

    BEGIN
    INSERT INTO westerostax.users (id, active, password, username, district_id, landowner_id, lord_id, region_id) VALUES
      (6, true, 'weqweqqwe', 4, NULL, NULL, 2, 1),
      (7, true, 23123, 'landowner2', NULL, NULL, 3, 2),
      (8, true, true, 'landowner3', NULL, NULL, 4, 3),
      (9, true, 1, 'landowner4', NULL, NULL, 5, 4);
  EXCEPTION
    WHEN others THEN
      RAISE NOTICE 'Error inserting user: %', SQLERRM;
  END;

    BEGIN
    INSERT INTO westerostax.districts (id, name, total_households, total_cattle_headcount, total_residents, total_taxes_paid, region_id) VALUES 
      (1, 'District1', 0, 0, 0, 'heh', 1),
      (2, 'District2', 0, 0, 'ah', 0, 2),
      (3, 'District3', 0, 0, '213', 0, 3),
      (4, 'District4', 'fedf', 0, 0, 0, 4);
  EXCEPTION
    WHEN others THEN
      RAISE NOTICE 'Error inserting user: %', SQLERRM;
  END;
  RAISE NOTICE '    Выполнены проверочные операции для таблиц';
  RAISE NOTICE ' ';
    -- END Неправильные блоки записи и обновления
  -- МЕЖДУ ЭТИМИ КОММЕНТАМИ БЛОКИ INSERT С ПРОВЕРКОЙ

  end_time := clock_timestamp();

  RAISE NOTICE '    Скрипт выполен успешно';
  RAISE NOTICE 'Script execution time: % seconds', (end_time - start_time);
END $$;