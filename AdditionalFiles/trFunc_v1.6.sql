-- обновление полей в таблице districts
CREATE OR REPLACE FUNCTION westerostax.update_district_totals()
RETURNS TRIGGER AS
$$
BEGIN
  BEGIN
    RAISE NOTICE 'Trigger update_district_totals_trigger fired!';

    IF TG_OP = 'INSERT' THEN
      UPDATE westerostax.districts
      SET total_households = CASE WHEN total_households IS NULL THEN 1 ELSE total_households + 1 END,
          total_cattle_headcount = CASE WHEN total_cattle_headcount IS NULL THEN NEW.cattle_headcount ELSE total_cattle_headcount + NEW.cattle_headcount END,
          total_residents = CASE WHEN total_residents IS NULL THEN NEW.resident_count ELSE total_residents + NEW.resident_count END,
          total_taxes_paid = CASE WHEN total_taxes_paid IS NULL THEN NEW.taxes_collected ELSE total_taxes_paid + NEW.taxes_collected END,
          total_income = CASE WHEN total_income IS NULL THEN NEW.income ELSE total_income + NEW.income END
      WHERE id = NEW.district_id;

    ELSIF TG_OP = 'UPDATE' THEN
      IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'households' AND column_name = 'total_households') THEN
        IF NEW.total_households IS NOT NULL THEN
          UPDATE westerostax.districts
          SET total_households = CASE WHEN total_households IS NULL THEN NEW.total_households ELSE total_households + NEW.total_households - OLD.total_households END,
              total_cattle_headcount = CASE WHEN total_cattle_headcount IS NULL THEN NEW.total_cattle_headcount ELSE total_cattle_headcount + NEW.total_cattle_headcount - OLD.total_cattle_headcount END,
              total_residents = CASE WHEN total_residents IS NULL THEN NEW.total_residents ELSE total_residents + NEW.total_residents - OLD.total_residents END,
              total_taxes_paid = CASE WHEN total_taxes_paid IS NULL THEN NEW.total_taxes_paid ELSE total_taxes_paid + NEW.total_taxes_paid - OLD.total_taxes_paid END,
              total_income = CASE WHEN total_income IS NULL THEN NEW.total_income ELSE total_income + NEW.total_income - OLD.total.income END
          WHERE id = NEW.district_id;
          RAISE NOTICE 'Update in update_district_totals_trigger: district_id=%, total_households=%, total_cattle_headcount=%', NEW.district_id, NEW.total_households, NEW.total_cattle_headcount;
        ELSE
          -- Обработка случая, когда NEW.total_households IS NULL
          -- В данном случае, поле total_households не будет обновлено.
        END IF;
      END IF;
    ELSIF TG_OP = 'DELETE' THEN
      UPDATE westerostax.districts
      SET total_households = CASE WHEN total_households IS NULL THEN -1 ELSE total_households - 1 END,
          total_cattle_headcount = CASE WHEN total_cattle_headcount IS NULL THEN -OLD.cattle_headcount ELSE total_cattle_headcount - OLD.cattle_headcount END,
          total_residents = CASE WHEN total_residents IS NULL THEN -OLD.resident_count ELSE total_residents - OLD.resident_count END,
          total_taxes_paid = CASE WHEN total_taxes_paid IS NULL THEN -OLD.taxes_collected ELSE total_taxes_paid - OLD.taxes_collected END,
          total_income = CASE WHEN total_income IS NULL THEN -OLD.income ELSE total_income - OLD.income END
      WHERE id = OLD.district_id;
    END IF;
  EXCEPTION
    WHEN others THEN
      RAISE EXCEPTION 'An error occurred in update_district_totals_trigger: %', SQLERRM;
  END;

  RETURN NEW;
END;
$$
LANGUAGE plpgsql;

-- триггер для таблицы households
CREATE TRIGGER update_district_totals_trigger
AFTER INSERT OR UPDATE OR DELETE ON westerostax.households
FOR EACH ROW
EXECUTE FUNCTION westerostax.update_district_totals();

-- обновление полей в таблице regions
CREATE OR REPLACE FUNCTION westerostax.update_region_totals()
RETURNS TRIGGER AS
$$
BEGIN
  BEGIN
    RAISE NOTICE 'Trigger update_region_totals_trigger fired!';

    IF TG_OP = 'INSERT' THEN
      UPDATE westerostax.regions
      SET total_households = CASE WHEN total_households IS NULL THEN NEW.total_households ELSE total_households + NEW.total_households END,
          total_cattle_headcount = CASE WHEN total_cattle_headcount IS NULL THEN NEW.total_cattle_headcount ELSE total_cattle_headcount + NEW.total_cattle_headcount END,
          total_residents = CASE WHEN total_residents IS NULL THEN NEW.total_residents ELSE total_residents + NEW.total_residents END,
          total_taxes_paid = CASE WHEN total_taxes_paid IS NULL THEN NEW.total_taxes_paid ELSE total_taxes_paid + NEW.total_taxes_paid END,
          total_income = CASE WHEN total_income IS NULL THEN NEW.total_income ELSE total_income + NEW.total_income END
      WHERE id = NEW.region_id;
    ELSIF TG_OP = 'UPDATE' THEN
      IF EXISTS (SELECT 1 FROM information_schema.columns WHERE table_name = 'districts' AND column_name = 'total_households') THEN
        IF NEW.total_households IS NOT NULL THEN
          UPDATE westerostax.regions
          SET total_households = CASE WHEN total_households IS NULL THEN NEW.total_households ELSE total_households + NEW.total_households - OLD.total_households END,
              total_cattle_headcount = CASE WHEN total_cattle_headcount IS NULL THEN NEW.total_cattle_headcount ELSE total_cattle_headcount + NEW.total_cattle_headcount - OLD.total_cattle_headcount END,
              total_residents = CASE WHEN total_residents IS NULL THEN NEW.total_residents ELSE total_residents + NEW.total_residents - OLD.total_residents END,
              total_taxes_paid = CASE WHEN total_taxes_paid IS NULL THEN NEW.total_taxes_paid ELSE total_taxes_paid + NEW.total_taxes_paid - OLD.total_taxes_paid END,
              total_income = CASE WHEN total_income IS NULL THEN NEW.total_income ELSE total_income + NEW.total_income - OLD.total_income END
          WHERE id = NEW.region_id;
          RAISE NOTICE 'Update in update_region_totals_trigger: region_id=%, total_households=%, total_cattle_headcount=%', NEW.region_id, NEW.total_households, NEW.total_cattle_headcount;
        ELSE
          -- Обработка случая, когда NEW.total_households IS NULL
          -- В данном случае, поле total_households не будет обновлено.
        END IF;
      END IF;
    ELSIF TG_OP = 'DELETE' THEN
      UPDATE westerostax.regions
      SET total_households = CASE WHEN total_households IS NULL THEN -1 ELSE total_households - 1 END,
          total_cattle_headcount = CASE WHEN total_cattle_headcount IS NULL THEN -OLD.total_cattle_headcount ELSE total_cattle_headcount - OLD.total_cattle_headcount END,
          total_residents = CASE WHEN total_residents IS NULL THEN -OLD.total_residents ELSE total_residents - OLD.total_residents END,
          total_taxes_paid = CASE WHEN total_taxes_paid IS NULL THEN -OLD.total_taxes_paid ELSE total_taxes_paid - OLD.total_taxes_paid END,
          total_income = CASE WHEN total_income IS NULL THEN -OLD.total_income ELSE total_income - OLD.total_income END
      WHERE id = OLD.region_id;
    END IF;
  EXCEPTION
    WHEN others THEN
      RAISE EXCEPTION 'An error occurred in update_region_totals_trigger: %', SQLERRM;
  END;

  RETURN NEW;
END;
$$
LANGUAGE plpgsql;

-- триггер для таблицы districts
CREATE TRIGGER update_region_totals_trigger
AFTER INSERT OR UPDATE OR DELETE ON westerostax.districts
FOR EACH ROW
EXECUTE FUNCTION westerostax.update_region_totals();