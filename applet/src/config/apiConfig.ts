const {
  VITE_API_URL,
  VITE_API_URL_PREFIX,
} = process.env
export const BASE_URL = VITE_API_URL! + VITE_API_URL_PREFIX
export const UPLOAD_URL = `${BASE_URL}/upload`
export const CODE_NAME = 'code'
export const MESSAGE_NAME = 'message'
export const DATA_NAME = 'result'
