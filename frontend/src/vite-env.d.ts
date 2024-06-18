// / <reference types="vite/client" />

interface ImportMeta {
	readonly env: ImportMetaEnv;
}

interface ImportMetaEnv {
	readonly VITE_API_KEY: string;
	// Add more environment variables as needed
}
