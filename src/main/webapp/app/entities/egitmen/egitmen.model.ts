export interface IEgitmen {
  id?: number;
  adiSoyadi?: string | null;
  unvani?: string | null;
  puani?: number | null;
}

export class Egitmen implements IEgitmen {
  constructor(public id?: number, public adiSoyadi?: string | null, public unvani?: string | null, public puani?: number | null) {}
}

export function getEgitmenIdentifier(egitmen: IEgitmen): number | undefined {
  return egitmen.id;
}
