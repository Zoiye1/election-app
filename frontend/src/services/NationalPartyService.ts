import type { PartyDetail, TopNationalParty } from '@/interfaces/IElectionData.ts'

/**
 *
 * Service for fetching national party results
 */
const API_BASE_URL = import.meta.env.VITE_API_BASE_URL;

export class NationalPartyService {


  /**
   * Fetches the top parties by votes for a specific election
   * @param electionId the election identifier (e.g., "TK2023")
   * @returns Promise with array of top national parties
   */
  public static async getTopParties(electionId: string): Promise<TopNationalParty[]> {
    const url: string = `${API_BASE_URL}/v1/parties/top?electionId=${electionId}`;
    // Check if request was successful
    try {
      const response: Response = await fetch(url, {
        method: "GET",
        headers: { "Accept": "application/json" }
      });
      // Check if request was successful
      if (!response.ok) {
        throw new Error("request failed " + response.statusText);
      }
      // Return parsed JSON response
      return await response.json();
    } catch (error) {
      // Log error and rethrow
      console.error('Fetch error:', error);
      throw error;
    }
  }

  /**
   * Fetches detailed party information including candidates
   * @param electionId the election identifier (e.g., "TK2023")
   * @param partyId the party identifier
   * @returns Promise with party details
   */
  public static async getPartyDetails(electionId: string, partyId: number): Promise<PartyDetail> {}
}
