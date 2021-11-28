export interface IEgitimTuru {
  id?: number;
  adi?: string | null;
  aciklama?: string | null;
}

export class EgitimTuru implements IEgitimTuru {
  constructor(public id?: number, public adi?: string | null, public aciklama?: string | null) {}
}

export function getEgitimTuruIdentifier(egitimTuru: IEgitimTuru): number | undefined {
  return egitimTuru.id;
}
