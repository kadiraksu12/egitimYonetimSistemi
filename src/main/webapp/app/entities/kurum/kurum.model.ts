export interface IKurum {
  id?: number;
  adi?: string | null;
  aciklama?: string | null;
}

export class Kurum implements IKurum {
  constructor(public id?: number, public adi?: string | null, public aciklama?: string | null) {}
}

export function getKurumIdentifier(kurum: IKurum): number | undefined {
  return kurum.id;
}
