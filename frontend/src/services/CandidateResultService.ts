import type { CandidateResult} from '@/interfaces/CandidateResult.ts'

const API_BASE_URL = 'http://localhost:8080/api'

export async function getTopCandidates(electionId: String): Promise<TopCandidate[]> {
  const response = await fetch(`${API_BASE_URL}/candidates/top?electionId=${electionId}`);
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    return await response.json();
}
