import type { CandidateResult } from '@/interfaces/topCandidate';

const API_BASE_URL = 'http://localhost:8080/api';

/**
 * Fetches the top candidates by votes for a specific election
 * @param {string} electionId - The election identifier (e.g., "TK2023")
 * @returns {Promise<CandidateResult[]>} List of top candidates with votes and party
 */
export async function getTopCandidatesByElection(electionId: string): Promise<CandidateResult[]> {
  try {
    const response = await fetch(`${API_BASE_URL}/candidates/top?electionId=${electionId}`);

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }

    return await response.json();
  } catch (error) {
    console.error('Error fetching top candidates:', error);
    throw error;
  }
}
