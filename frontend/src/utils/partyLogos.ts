
  /**
   * Mapping for partyLogo svg files to party names
   */
  const partyLogoMap: Record<string, string> = {}

  /**
   * Gets the logo filename for a party
   * @param partyName - Party name from database
   * @returns Logo filename or null if not found
   */
  export function getPartyLogo(partyName: string): string | null {
    return partyLogoMap[partyName] ?? null
  }

