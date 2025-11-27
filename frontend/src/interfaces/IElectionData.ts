export interface Party {
  id: number
  partyName: string;
}

export interface Constituency{
  id: number;
  name: string;
}

export interface ConstituencyPartyVotes {
  id: number
  partyName: string;
  constituency: Constituency;
  votes: number
}

export interface Election {
  id: string;
  totalCounted?: number;
  partyResults?: Party[];
  constituencyPartyVotes: ConstituencyPartyVotes[];
}

export interface Municipality {
  id: number;
  name: string;
}

export interface MunicipalityPartyVotes {
  id: number;
  partyName: string;
  municipality: Municipality;
  votes: number;
}

export interface MunicipalityElection {
  id: string;
  totalCounted?: number;
  partyResults?: Party[];
  municipalityPartyVotes: MunicipalityPartyVotes[];
}

/**
 * Interface for top national party results
 */
export interface TopNationalParty {
  partyName: string;
  votes: number;
}
