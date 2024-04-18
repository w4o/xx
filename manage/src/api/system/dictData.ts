import request from '/@/utils/request';

export function useDictDataApi() {
	return {
		findPage: (params?: object) => {
			return request({
				url: '/sys/dictData',
				method: 'get',
				params,
			});
		},
	};
}
