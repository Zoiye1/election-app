export class CustomLogger {

  private getTimestamp(): string {
    return new Date().toISOString(); // "2024-12-18T14:30:45.123Z"
  }

  private formatMessage(level: string, message: string): string {
    return `[${this.getTimestamp()}] [${level}] ${message}`;
  }

  public error(message: string, error: unknown): void {
    const formatted = this.formatMessage('ERROR', message);

    if (error instanceof Error) {
      console.error(formatted);
      console.error('Error message:', error.message);
      console.error('Stack trace:', error.stack);
    } else {
      console.error(formatted, error);
    }
  }

  public warn(message: string): void {
    console.warn(this.formatMessage('WARNING', message));
  }

  public info(message: string): void {
    console.info(this.formatMessage('INFO', message));
  }

  public debug(message: string): void {
    console.debug(this.formatMessage('DEBUG', message));
  }
}
