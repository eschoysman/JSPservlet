ALTER TABLE "LogOperazioniANSC" ALTER COLUMN "eseguita" TYPE character varying(16);
ALTER TABLE "LogOperazioniANSC" ALTER COLUMN "idAtto" TYPE bigint using ("idAtto"::bigint);