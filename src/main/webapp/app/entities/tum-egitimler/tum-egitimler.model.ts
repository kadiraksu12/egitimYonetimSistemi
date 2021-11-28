import * as dayjs from 'dayjs';

export interface ITumEgitimler {
  id?: number;
  egitimBaslik?: string | null;
  egitimAltBaslik?: string | null;
  egitimBaslamaTarihi?: dayjs.Dayjs | null;
  egitimBitisTarihi?: dayjs.Dayjs | null;
  dersSayisi?: number | null;
  egitimSuresi?: number | null;
  egitimYeri?: string | null;
  egitimPuani?: number | null;
  kayit?: boolean | null;
}

export class TumEgitimler implements ITumEgitimler {
  constructor(
    public id?: number,
    public egitimBaslik?: string | null,
    public egitimAltBaslik?: string | null,
    public egitimBaslamaTarihi?: dayjs.Dayjs | null,
    public egitimBitisTarihi?: dayjs.Dayjs | null,
    public dersSayisi?: number | null,
    public egitimSuresi?: number | null,
    public egitimYeri?: string | null,
    public egitimPuani?: number | null,
    public kayit?: boolean | null
  ) {
    this.kayit = this.kayit ?? false;
  }
}

export function getTumEgitimlerIdentifier(tumEgitimler: ITumEgitimler): number | undefined {
  return tumEgitimler.id;
}
