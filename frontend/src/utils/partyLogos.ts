
  /**
   * Mapping for partyLogo svg files to party names
   */
  const partyLogoMap: Record<string, string> = {
    "VVD": "vvd.svg",
    "D66": "d66.svg",
    "CDA": "cda.svg",
    "PVV (Partij voor de Vrijheid)": "partijVoorDeVrijheid.svg",
    "SP (Socialistische Partij)": "socialistischePartij.svg",
    "Partij van de Arbeid (P.v.d.A.)": "partijVanDeArbeid.svg",
    "GROENLINKS": "groenLinks.svg",
    "ChristenUnie": "christenUnie.svg",
    "Partij voor de Dieren": "partijVanDeDieren.svg",
    "Forum voor Democratie": "forumVoorDemocratie.svg",
    "Staatkundig Gereformeerde Partij (SGP)": "staatkundigGereformeerdePartij.svg",
    "DENK": "denk.svg",
    "50PLUS": "50plus.svg",
    "BBB": "bbb.svg",
    "JA21": "ja21.svg",
    "Volt": "volt.svg",
    "BIJ1": "bij1.svg",
    "CODE ORANJE": "codeOranje.svg",
    "Piratenpartij": "piratenPartij.svg",
    "LP (Libertaire Partij)": "libertairePartij.svg",
    "JEZUS LEEFT": "jezusLeeft.svg",
    "Trots op Nederland (TROTS)": "trots.svg",
    "NLBeter": "nlBeter.svg",
    "Partij van de Eenheid": "partijVanDeEenheid.svg",
    "De Groenen": "deGroenen.svg",
    "Vrij en Sociaal Nederland": "vsn.svg",


    "Nieuw Sociaal Contract": "nsc.svg",
    "Nieuw Sociaal Contract (NSC)": "nsc.svg",
    "BVNL / Groep Van Haga": "bnvl.svg",
    "Belang Van Nederland (BVNL)": "bnvl.svg",
    "GROENLINKS / Partij van de Arbeid (PvdA)": "groenLinks.svg",
    "Piratenpartij - De Groenen": "piratenPartij.svg",
  }


  /**
   * Gets the logo filename for a party
   * @param partyName - Party name from database
   * @returns Logo filename or null if not found
   */
  export function getPartyLogo(partyName: string): string | null {
    return partyLogoMap[partyName] ?? null
  }

