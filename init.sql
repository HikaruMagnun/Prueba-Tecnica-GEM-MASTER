DO $$
BEGIN
    IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'spring_reactive') THEN
        CREATE DATABASE spring_reactive;
    END IF;
END $$;